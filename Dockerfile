FROM ubuntu:latest
LABEL authors="tedga"

ENTRYPOINT ["top", "-b"]