FROM mysql:latest

# Environment variables for MySQL
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=classes
ENV MYSQL_USER=het
ENV MYSQL_PASSWORD=hetu2344

# Copy the initialization SQL script to the Docker container
COPY db.sql /docker-entrypoint-initdb.d

# Expose the default MySQL port
EXPOSE 3306
