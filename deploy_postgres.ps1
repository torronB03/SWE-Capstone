# PowerShell script to deploy PostgreSQL container

$DB_CONTAINER_NAME = "postgres_database_container"
$DB_PORT = 5432
$DB_USERNAME = "postgres"
$DB_PASSWORD = "postgres"
$DB_NAME = "resources_db"

# Get the folder path where this script is located
$ScriptDir = Split-Path -Parent $MyInvocation.MyCommand.Definition
#$HOST_DB_FOLDER = Join-Path $ScriptDir "SQL_Scripts\data"
$HOST_DB_FOLDER = Join-Path $ScriptDir "src\sql\data"

$DESTINATION_PATH = "/data"

# Convert the Windows path to a Unix-style path that Docker can understand
$unixPath = $HOST_DB_FOLDER.Replace("C:\", "/c/").Replace("\", "/")

# Check if Docker is running
docker info

# Stop and remove any existing PostgreSQL container
docker stop $DB_CONTAINER_NAME
docker rm -f $DB_CONTAINER_NAME

# Build the docker run command as a single string
$dockerRunCommand = "docker run --name $DB_CONTAINER_NAME -e POSTGRES_PASSWORD=$DB_PASSWORD -e POSTGRES_DB=$DB_NAME -p ${DB_PORT}:${DB_PORT} -v ${unixPath}:${DESTINATION_PATH} -d postgres:latest"

Write-Host "Running command:`n$dockerRunCommand"

# Execute the docker run command
Invoke-Expression $dockerRunCommand

# Wait for container to initialize
Start-Sleep -Seconds 10

# Test PostgreSQL connection
docker exec -it $DB_CONTAINER_NAME psql -U $DB_USERNAME -d $DB_NAME -c "SELECT 1;"

Write-Host "PostgreSQL deployment completed and connection tested!"
Write-Host "Deploy using the username: $DB_USERNAME"
Write-Host "Password: $DB_PASSWORD"
Write-Host "Port: $DB_PORT"
Write-Host "Database Name: $DB_NAME"
