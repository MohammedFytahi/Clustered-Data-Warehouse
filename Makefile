PHONY: up down build run test clean logs restart

up:
	docker compose up -d

down:
	docker compose down

build:
	docker compose build

run:
	mvn spring-boot:run

test:
	mvn test

clean:
	mvn clean
	rm -rf target/

logs:
	docker compose logs -f

restart:
	docker compose down && docker compose up -d
