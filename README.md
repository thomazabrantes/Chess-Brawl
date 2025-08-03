<h1 align="center">ChessBrawl</h1>

<p align="center"> Thomaz Abrantes de Oliveira Martinelli Silva</p>

---

## Overview
My project consists of a complete chess tournament simulation system developed in Java, based on Spring Boot and organized following the principles of Clean Architecture. It includes both a functional backend and a simple, intuitive frontend, allowing users to easily take advantage of the various implemented features without major issues.

I divided my system into layers, which can be described as follows:

- A domain layer, which contains all the business rules through entities (Player, Battle, Event, Round, and Tournament) mapped with JPA for data persistence in the database (data.sql), along with repositories that access this database;

- An application layer, responsible for storing the use cases used to implement each of the features required in the project prompt. This layer also includes DTO classes (used to encapsulate data for transfer between layers) and mappers (which help map objects or data), facilitating communication with the frontend;

- An infrastructure layer, where the REST controllers are located (they create endpoints for the services represented by the use cases).

- Additionally, there is a resources layer, where the database and frontend components (HTML, CSS, and JavaScript) are located. It is also worth mentioning that the entire project was developed using comments, variable names, and method names in English, in order to reach a broader target audience.

---

## How to execute the project APP
<h3>Finding the Main class</h3>
<p>First, you have to look for the "Main.java" which is the main class. You can find the class "Main.java" following this path: 

Dell - IT Academy\Project\CleanArch\ChessBrawl\src\main\java\br\pucrs\thomaz\ChessBrawl\Main.java

<h3>Running the app</h3>
Once you have found the correct class, you can click on the run program button, which is located in the upper right corner (equivalent to the fn + F5 keys or just F5, if you are using Visual Studio Code).</p>

<h3>Finding the app</h3>
<p>Considering that you have already managed to follow all the previous steps, the next step to take is to open a browser of your choice and access: <a href ="http://localhost:8080/">http://localhost:8080/</a>.</p>

<p>Another option is the following:</p>

1. **Click on the "PORTS" tab** at the top of the Visual Studio Code interface.
   - This tab shows which ports are being used by your application (for example, port `8080`).

2. Inside the **PORTS** tab, locate the **"Forwarded Address"** column.
   - Click the **blue link** shown next to the port your application is running on.

<p align="center">
  <img src="https://github.com/thomazabrantes/Chess-Brawl/blob/main/Dell%20-%20IT%20Academy/Project/CleanArch/ChessBrawl/images/8080.png" alt="port 8080">
</p>

<p>Then, you shall see the following interface:</p>

<p align="center">
  <img src="https://github.com/thomazabrantes/Chess-Brawl/blob/main/Dell%20-%20IT%20Academy/Project/CleanArch/ChessBrawl/images/interfaceChessBrawl.png" alt="image print" alt="image print">
</p>

<h3>Testing the requests</h3>
<p>
Now you can simulate your own chess tournament! You have the following features available via the frontend interface:
</p>

<ol>
  <li><strong>View Players</strong><br>
    Displays all registered players along with their ranking and tournament points.
  </li>

  <li><strong>Register Player</strong><br>
    Prompts the user to input a name, nickname, and ranking (1 to 15000). The player is added to the system and appears in the player list.
  </li>

  <li><strong>Create Tournament</strong><br>
    Prompts for a comma-separated list of player nicknames (minimum 4 and maximum 8, even number only). A tournament is created with those players.
  </li>

  <li><strong>Begin Tournament</strong><br>
    Prompts for the tournament ID and starts the first round by pairing players into battles.
  </li>

  <li><strong>Battle Details</strong><br>
    Shows information about a specific battle. Prompts for tournament ID, round number, and battle index.
  </li>

  <li><strong>Register a Battle Event</strong><br>
    Records an in-game event for a specific player in a battle. Prompts for battle ID, player ID, and event type (chosen from a numeric menu):
    <ul>
      <li>1 - ORIGINAL_MOVE</li>
      <li>2 - MISTAKE</li>
      <li>3 - ADVANTAGEOUS_POSITION</li>
      <li>4 - DISRESPECT_TO_OPPONENT</li>
      <li>5 - FURY_ATTACK</li>
    </ul>
    Points and penalties are automatically applied to the player.
  </li>

  <li><strong>Finalize Battle</strong><br>
    Prompts for the battle ID. Automatically determines the winner (considering a blitz match in case of a tie) and updates tournament scores.
  </li>

  <li><strong>Advance Round</strong><br>
    Prompts for the tournament ID. Proceeds to the next round, if all battles of the current round have been finalized.
  </li>

  <li><strong>Tournament Results</strong><br>
    Prompts for the tournament ID. Displays a ranked table with all players, sorted by tournament points and listing event statistics.
  </li>

  <li><strong>Champion</strong><br>
    Prompts for the tournament ID. Displays the final champion of the tournament (after all rounds are completed).
  </li>

  <li><strong>Player Events</strong><br>
    Prompts for a player ID and displays a chronological list of all in-game events the player participated in.
  </li>
</ol>

<p>
Explore each feature to experience a simulated ChessBrawl tournament!
</p>

---

<p align="center">
</p>
