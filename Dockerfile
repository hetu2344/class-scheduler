# Use the official MySQL image from the Docker Hub
FROM mysql:latest

# Set environment variables
ENV MYSQL_ROOT_PASSWORD=root_password
ENV MYSQL_DATABASE=my_database
ENV MYSQL_USER=new_user
ENV MYSQL_PASSWORD=new_password

# Expose port 3306 to the host
EXPOSE 3306
