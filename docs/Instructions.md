1. Clone the Repository:
   ```bash
   git clone https://github.com/COSC-A451-Spring-2025-S-W-Engineering/Project-COSC-A451-2025-01-TR-Sw-Egrg.git
   cd Project-COSC-A451-2025-01-TR-Sw-Egrg
   ```

2. Requirements (must be installed):
   - Docker Desktop  
   - Git Bash (for Windows)  
   - PostgreSQL with PostGIS extension  
   - **Java Development Kit (JDK 17 only)** — for building and running the web application  
   - DataGrip (optional for DB viewing)

3. Start Docker Desktop:
   Make sure it is running before continuing.

4. Build and Deploy the App:

   Run these commands from the project root:
   ./scripts/deploy.sh all


   ```bash
   ./scripts/deploy.sh all
   ```

   This script will:
   - Compile the web application  
   - Package it into a WAR file  
   - Spin up Docker containers and deploy the app to a Tomcat server

5. Access the Application:
   Once deployment is complete, open your browser and go to:
   [http://localhost:8080/community-resources-app/pages/home.html](http://localhost:8080/community-resources-app/pages/home.html)

   You should see the home page for the LOYNO Community Resource Map.


## Troubleshooting

- Check if containers are running:
  ```bash
  docker ps
  ```

- Stop containers:
  ```bash
  docker stop cr-app
  docker stop cr-db
  ```

- Rebuild and restart everything:
  ```bash
  ./scripts/deploy.sh all
  ```



- 'psql' command to launch Postgres terminal connected to the database
   ```bash
  $ docker exec -it cr-db psql -U postgres
  ```
