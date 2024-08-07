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

# Retrive system usage info
vmstat_mb=$(vmstat --unit M)

timestamp=$(date +"%Y-%m-%d %H:%M:%S")
hostname=$(hostname -f)
memory_free=$(echo "$vmstat_mb" | awk '{print $4}' | tail -n1 | xargs)
cpu_idle=$(vmstat --unit M | tail -1 | awk '{print $15}')
cpu_kernel=$(vmstat --unit M | tail -1 | awk '{print $14}')
disk_io=$(vmstat -d | tail -1 | awk -v col="10" '{print $col}')
disk_available=$(df -BM / | tail -1 | awk '{print substr($4, 1, length($4)-1)}')

# Create subquery to retrive the foreign key for new data row
host_id="(SELECT id FROM host_info WHERE hostname='$hostname')";

# Create the sql insert statement with the new data
insert_stmt="INSERT INTO host_usage (timestamp, host_id, memory_free, cpu_idle, cpu_kernel, disk_io, disk_available) VALUES('$timestamp', $host_id, '$memory_free', '$cpu_idle', '$cpu_kernel', '$disk_io', '$disk_available');"

# Run the insert command on the database using the psql cli
export PGPASSWORD=$psql_password
psql -h $psql_host -U $psql_user -d $db_name -c "$insert_stmt"

exit $?
