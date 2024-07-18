# Use the official OpenJDK 18 image as the base image
FROM openjdk:18-jdk-slim

# Set the working directory inside the container
WORKDIR /class-scheduler

# Install SQLite and tree
RUN apt-get update && \
    apt-get install -y sqlite3 libsqlite3-dev wget tree && \
    rm -rf /var/lib/apt/lists/*

# Add SQLite JDBC driver
RUN wget -O /class-scheduler/sqlite-jdbc.jar https://github.com/xerial/sqlite-jdbc/releases/download/3.39.3.0/sqlite-jdbc-3.39.3.0.jar

# Copy the current directory contents into the container at /class-scheduler
COPY . /class-scheduler

# Run tree to show the directory structure
RUN tree /class-scheduler

# Compile the Java application
RUN javac -d bin/main/java src/main/java/com/scheduler/application/Main.java

# Run the Java application with additional debugging information
CMD ["java", "-cp", "bin/main/java:/class-scheduler/sqlite-jdbc.jar", "com.scheduler.application.Main"]
