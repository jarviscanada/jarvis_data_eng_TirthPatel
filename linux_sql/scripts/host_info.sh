# Setup user specified arguments
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

# Validate number of args
if [ "$#" -ne 5 ]; then
    echo "Illegal number of parameters"
    exit 1
fi

# Retrieve system hardware info
lscpu_out=`lscpu`

timestamp=$(date +"%Y-%m-%d %H:%M:%S")
hostname=$(hostname -f)
cpu_number=$(echo "$lscpu_out" | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out"  | egrep "^Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out"  | egrep "^Model name:" | awk -F':[[:space:]]*' '{print $2}' | xargs)
cpu_mhz=$(cat /proc/cpuinfo | grep "cpu MHz" | awk '{print $4}' | uniq | xargs)
l2_cache=$(echo "$lscpu_out"  | egrep "^L2 cache:" | awk '{print $3}' | xargs)
total_mem=$(vmstat --unit M | tail -1 | awk '{print $4}' | xargs)

# Create the sql insert statement with the new data
insert_stmt="INSERT INTO host_info (hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, l2_cache, timestamp, total_mem) VALUES('$hostname', '$cpu_number', '$cpu_architecture', '$cpu_model', '$cpu_mhz', '$l2_cache', '$timestamp', '$total_mem');"

# Run the insert command on the database using the psql cli
export PGPASSWORD=$psql_password
psql -h $psql_host -U $psql_user -d $db_name -c "$insert_stmt"

exit $?
