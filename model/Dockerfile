# Generated in [2019-01-01 00:00:00]
FROM registry:5000/jdk8:base
# Expose the API port
EXPOSE 8080
ADD target /home/listen/Apps/
ADD startup.sh /home/listen/Apps/
# Run the shell
WORKDIR /home/listen/Apps/
RUN chmod +x ./startup.sh
CMD ./startup.sh