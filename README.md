# Create new app
Follow this guide to create a new Ktor project manually in terminal.

```bash
# Install SDKMAN and install JDK 17 (Zulu)
brew install sdkman
sdk install 17.0.8.1-zulu

# Install Gradle
sdk install gradle

# Create new directory for the project
mkdir blog-api-ktor && cd ./blog-api-ktor

# Initialize a Gradle project
gradle init --type kotlin-application

# Add necessary Ktor dependencies into app/build.gradle.kts

# Run the application
gradle run

# Refresh the dependencies
gradle build --refresh-dependencies

# Configure the app to use Kotlin/Native

# Install Postgres driver
## macOS
brew install libpq
## ubuntu
apt-get install libpq-dev
```

# TODO
- Setup MVC structure
- Add CRUD
- Setup ORM (Prisma)
- Setup Swagger
- Setup all the other necessary stuff
- Setup Docker
