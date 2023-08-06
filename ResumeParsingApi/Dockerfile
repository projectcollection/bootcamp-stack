# syntax=docker/dockerfile:1

FROM python:3.7

WORKDIR /app
COPY . /app
RUN pip install -r /app/requirements.txt
RUN ["chmod", "+x", "./setup-science.sh"]
RUN /app/setup-science.sh
CMD ["flask", "run", "--host", "0.0.0.0"]
