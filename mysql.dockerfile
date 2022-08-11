FROM mysql:8.0

COPY ./mysql/config/mysql.cnf /etc/mysql/conf.d/my.cnf

ENV MYSQL_ROOT_PASSWORD="root" \
    MYSQL_DATABASE="gc2_db" \
    TZ="Asia/Tokyo"

CMD ["mysqld"]
