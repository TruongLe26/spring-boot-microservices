FROM mysql:8.2.0
MAINTAINER "truonglq"
EXPOSE 6033

COPY 01-init.sql /docker-entrypoint-initdb.d/
COPY 02-data.sql /docker-entrypoint-initdb.d/

ENTRYPOINT ["docker-entrypoint.sh"]
CMD ["mysqld"]