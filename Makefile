run: build
	java -jar app/build/libs/app-1.0.0.jar

build:
	./gradlew shadowJar

clean:
	./gradlew clean
