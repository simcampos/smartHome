FROM mariadb:10.11.8

# Set the working directory
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=smarthome
ENV MYSQL_USER=smarthome
ENV MYSQL_PASSWORD=smarthome

# Copy the database schema to the container
EXPOSE 3306

CMD ["mysqld"]