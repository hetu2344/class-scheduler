run: build
	java -jar app/build/libs/app-1.0.0.jar --port=8081

build:
	./gradlew shadowJar

clean:
	./gradlew clean
