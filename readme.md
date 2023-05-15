# About

This is a fully backend incremental game. I made this just for portfolio and this is not meant to be a serious project.

## Usage

Run the program and use postman to "play".

Current endpoints:
   POST http://localhost:8080/player/create?name=test - create player with name
   GET http://localhost:8080/player/info?name=test - get player info by name
   POST http://localhost:8080/resource/generator/buy?playerName=test&generatorName=Wood Generator - build a generator with specific name for player with specific name

