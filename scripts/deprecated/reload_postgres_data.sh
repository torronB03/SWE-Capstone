#!/bin/bash

CONTAINER=communityhub-db
DB=resources_db
USER=postgres

docker-compose down -v
docker-compose up -d

echo "⏳ Loading schema..."
docker exec -i $CONTAINER bash -c "psql -U $USER -d $DB -f src/sql/community_resource_schema_postgis.sql"

echo "✅ Schema loaded"

echo "⏳ Creating tables..."
docker exec -i $CONTAINER bash -c "psql -U $USER -d $DB -f src/sql/create_tables_v3.sql"

echo "✅ Tables created"

echo "⏳ Inserting data..."
docker exec -i $CONTAINER bash -c "psql -U $USER -d $DB -f src/sql/insert_scripts.sql"

echo "✅ Data insert completed!"
