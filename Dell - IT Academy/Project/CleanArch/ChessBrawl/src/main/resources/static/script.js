async function loadPlayers() {
    const response = await fetch('/servcad/players');
    const data = await response.json();
    
    const output = document.getElementById('output');
    output.innerHTML = '<h2 style="color: green;">Players:</h2>';

    const list = document.createElement('ul');
    list.style.listStyle = 'none';
    list.style.padding = '0';

    data.forEach(player => {
        const item = document.createElement('li');
        item.style.marginBottom = '10px';
        item.innerHTML = `
            <strong>${player.nickname}</strong> (${player.name})<br>
            Ranking Points: ${player.rankingPoints} | Tournament Points: ${player.tournamentPoints}
        `;
        list.appendChild(item);
    });

    output.appendChild(list);
}

async function registerPlayer() {
    const name = prompt("Player name:");
    if (!name) return;

    const nickname = prompt("Player nickname:");
    if (!nickname) return;

    const rankingPoints = prompt("Ranking Points (1-15000):");
    if (!rankingPoints || isNaN(rankingPoints)) {
        alert("You must provide a valid number (1-15000).");
        return;
    }

    const response = await fetch('/servcad/players', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({ name, nickname, rankingPoints: parseInt(rankingPoints) })
    });

    if (response.ok) {
        const player = await response.json();
        const output = document.getElementById('output');
        output.innerHTML = `
            <h2 style="color: green;">Player Registered!</h2>
            <p><strong>${player.nickname}</strong> (${player.name}) - Ranking: ${player.rankingPoints}</p>
        `;
    } else {
        alert("Error registering player.");
    }
}

async function createTournament() {
    const nicknames = prompt("Enter nicknames separated by commas:");
    if (!nicknames) return;

    const response = await fetch('/servcad/tournament/create', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(nicknames.split(',').map(n => n.trim()))
    });

    const data = await response.json();
    const output = document.getElementById('output');
    output.innerHTML = `<h2 style="color: green;">Tournament created (ID ${data.code})</h2>`;

    const list = document.createElement('ul');
    data.players.forEach(player => {
        const item = document.createElement('li');
        item.innerHTML = `
            <strong>${player.nickname}</strong> (${player.name}) - Ranking: ${player.rankingPoints}
        `;
        list.appendChild(item);
    });

    output.appendChild(list);
}


async function startTournament() {
    const id = prompt("Enter tournament code:");
    if (!id) return;

    const response = await fetch(`/servcad/tournament/${id}/start`, { method: 'POST' });
    const data = await response.json();

    const output = document.getElementById('output');
    output.innerHTML = `<h2 style="color: green;">Tournament Started (ID ${data.code}) - Round ${data.rounds.length}</h2>`;

    const round = data.rounds[data.rounds.length - 1];
    const list = document.createElement('ul');

    round.battles.forEach((battle, index) => {
        const item = document.createElement('li');
        item.innerHTML = `
            <strong>Battle ${index + 1}</strong>: ${battle.playerANickname} vs ${battle.playerBNickname}
        `;
        list.appendChild(item);
    });

    output.appendChild(list);
}

async function administerBattle() {
    const tournamentCode = prompt("Enter tournament code:");
    const roundNumber = prompt("Enter round number:");
    const battleIndex = prompt("Enter the battle index (starts at 0):");

    if (!tournamentCode || !roundNumber || !battleIndex) {
        alert("All fields are required.");
        return;
    }

    const response = await fetch(`/servcad/battles/select?tournamentCode=${tournamentCode}&roundNumber=${roundNumber}&battleIndex=${battleIndex}`);
    
    if (response.ok) {
        const battle = await response.json();
        const output = document.getElementById('output');
        output.innerHTML = `
            <h2 style="color: green;">Battle Details</h2>
            <p><strong>Player A:</strong> ${battle.playerANickname} (ID ${battle.playerACode})</p>
            <p><strong>Player B:</strong> ${battle.playerBNickname} (ID ${battle.playerBCode})</p>
            <p><strong>Winner:</strong> ${battle.winnerCode ? "ID " + battle.winnerCode : "To be defined"}</p>
            <p><strong>Score:</strong> ${battle.playerAScore} x ${battle.playerBScore}</p>
            <p><strong>Finished:</strong> ${battle.finished ? "Yes" : "No"}</p>
        `;
    } else {
        alert("Error searching for battle. Please check the data and try again.");
    }
}

async function viewBattleDetails() {
    const tournamentCode = prompt("Enter tournament code:");
    const roundNumber = prompt("Enter the round number:");
    const battleIndex = prompt("Enter the battle index (starts at 0):");

    if (!tournamentCode || !roundNumber || !battleIndex) {
        alert("All fields are required.");
        return;
    }

    const response = await fetch(`/servcad/battles/details?tournamentCode=${tournamentCode}&roundNumber=${roundNumber}&battleIndex=${battleIndex}`);

    if (response.ok) {
        const battle = await response.json();
        const output = document.getElementById('output');
        output.innerHTML = `
            <h2 style="color: green;">Battle Details</h2>
            <p><strong>Battle ID:</strong> ${battle.code}</p>
            <p><strong>Player A:</strong> ${battle.playerANickname} (ID ${battle.playerACode})</p>
            <p><strong>Player B:</strong> ${battle.playerBNickname} (ID ${battle.playerBCode})</p>
            <p><strong>Winner:</strong> ${battle.winnerCode ? "ID " + battle.winnerCode : "Not defined yet"}</p>
            <p><strong>Blitz Match:</strong> ${battle.blitzMatchUsed ? "Yes" : "No"}</p>
            <p><strong>Finished:</strong> ${battle.finished ? "Yes" : "No"}</p>
            <p><strong>Score:</strong> ${battle.playerAScore} x ${battle.playerBScore}</p>
        `;
    } else {
        alert("Error fetching battle details. Please check the data.");
    }
}

async function registerBattleEvent() {
    const battleId = prompt("Enter Battle ID:");
    const playerId = prompt("Enter Player ID:");

    const eventChoice = prompt(
        `Choose event type:\n` +
        `1 - ORIGINAL_MOVE\n` +
        `2 - MISTAKE\n` +
        `3 - ADVANTAGEOUS_POSITION\n` +
        `4 - DISRESPECT_TO_OPPONENT\n` +
        `5 - FURY_ATTACK`
    );

    const eventMap = {
        1: "ORIGINAL_MOVE",
        2: "MISTAKE",
        3: "ADVANTAGEOUS_POSITION",
        4: "DISRESPECT_TO_OPPONENT",
        5: "FURY_ATTACK"
    };

    const eventType = eventMap[parseInt(eventChoice)];

    if (!battleId || !playerId || !eventType) {
        alert("All fields are required and event must be valid.");
        return;
    }

    const response = await fetch(`/servcad/battles/${battleId}/events?playerId=${playerId}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ type: eventType })
    });

    const output = document.getElementById('output');

    if (response.ok) {
        output.innerHTML = `<p style="color: green;"><strong>Event registered successfully!</strong></p>`;
    } else {
        output.innerHTML = `<p style="color: red;"><strong>Error registering event. Check the data and try again.</strong></p>`;
    }
}


async function finalizeBattle() {
    const battleId = prompt("Enter the ID of the battle to be finalized:");
    if (!battleId) {
        alert("Battle ID is required.");
        return;
    }

    const response = await fetch(`/servcad/battles/${battleId}/finalize`, {
        method: 'POST'
    });

    const output = document.getElementById('output');

    if (response.ok) {
        const data = await response.json();
        output.innerHTML = `
            <h3 style="color: green;">Battle Finalized</h3>
            <p><strong>ID:</strong> ${data.code}</p>
            <p><strong>Player A:</strong> ${data.playerANickname} (${data.playerAScore} pts)</p>
            <p><strong>Player B:</strong> ${data.playerBNickname} (${data.playerBScore} pts)</p>
            <p><strong>Winner:</strong> ${data.winnerCode}</p>
            <p><strong>Blitz Match:</strong> ${data.blitzMatchUsed ? "Yes" : "No"}</p>
        `;
    } else {
        output.innerHTML = `<p style="color: red;"><strong>Error finalizing the battle.</strong></p>`;
    }
}

async function advanceRound() {
    const tournamentId = prompt("Enter the tournament ID:");
    if (!tournamentId) {
        alert("Tournament ID is required.");
        return;
    }

    const response = await fetch(`/servcad/tournament/${tournamentId}/advance`, {
        method: 'POST'
    });

    const output = document.getElementById('output');

    if (response.ok) {
        const data = await response.json();

        const battlesHTML = data.battles.map(b => `
            <li>
                <strong>ID:</strong> ${b.code} |
                <strong>${b.playerANickname}</strong> vs <strong>${b.playerBNickname}</strong>
            </li>
        `).join("");

        output.innerHTML = `
            <h3 style="color: green;">New Round Created</h3>
            <p><strong>Round Number:</strong> ${data.roundNumber}</p>
            <p><strong>Finalized:</strong> ${data.finished ? "Yes" : "No"}</p>
            <h4>Batalhas:</h4>
            <ul>${battlesHTML}</ul>
        `;
    } else {
        output.innerHTML = `<p style="color: red;"><strong>Error advancing the round. Check if the current round is finished.</strong></p>`;
    }
}

async function showTournamentResults() {
    const tournamentId = prompt("Enter the tournament ID:");
    if (!tournamentId) {
        alert("Tournament ID is required.");
        return;
    }

    const response = await fetch(`/servcad/tournament/${tournamentId}/results`);
    const output = document.getElementById('output');

    if (response.ok) {
        const players = await response.json();

        const rows = players.map(p => `
            <tr>
                <td>${p.nickname}</td>
                <td>${p.name}</td>
                <td>${p.rankingPoints}</td>
                <td>${p.tournamentPoints}</td>
                <td>${p.originalMove}</td>
                <td>${p.mistake}</td>
                <td>${p.advantageousPosition}</td>
                <td>${p.disrespectToOpponent}</td>
                <td>${p.furyAttack}</td>
            </tr>
        `).join("");

        output.innerHTML = `
            <h3 style="color: green;">Tournament Results</h3>
            <table border="1" style="border-collapse: collapse; width: 100%;">
                <thead>
                    <tr>
                        <th style="color: green;">Nickname</th>
                        <th style="color: green;">Name</th>
                        <th style="color: green;">Ranking</th>
                        <th style="color: green;">Tournament Points</th>
                        <th style="color: green;">Original Move</th>
                        <th style="color: green;">Mistake</th>
                        <th style="color: green;">Advantageous Position</th>
                        <th style="color: green;">Disrespect</th>
                        <th style="color: green;">Fury Attack</th>
                    </tr>
                </thead>
                <tbody>
                    ${rows}
                </tbody>
            </table>
        `;
    } else {
        output.innerHTML = `<p style="color: red;"><strong>Error loading tournament results.</strong></p>`;
    }
}

async function showChampion() {
    const tournamentId = prompt("Enter the tournament ID:");
    if (!tournamentId) {
        alert("Tournament ID is required.");
        return;
    }

    const response = await fetch(`/servcad/tournament/${tournamentId}/champion`);
    const output = document.getElementById('output');

    if (response.ok) {
        const champion = await response.json();
        output.innerHTML = `
            <h3 style="color: green;">🏆 Champion</h3>
            <p><strong>Name:</strong> ${champion.name}</p>
            <p><strong>Nickname:</strong> ${champion.nickname}</p>
            <p><strong>Ranking:</strong> ${champion.rankingPoints}</p>
            <p><strong>Tournament Points:</strong> ${champion.tournamentPoints}</p>
        `;
    } else {
        output.innerHTML = `<p style="color: red;"><strong>There is no champion or there was an error in the request.</strong></p>`;
    }
}

async function showPlayerEvents() {
    const playerId = prompt("Enter Player ID:");
    if (!playerId) {
        alert("Player ID is required.");
        return;
    }

    try {
        const response = await fetch(`/servcad/players/${playerId}/events`);
        const output = document.getElementById('output');

        if (!response.ok) {
            throw new Error("Server response error");
        }

        const history = await response.json();
        console.log("History received:", history);

        if (!history.eventTypes || history.eventTypes.length === 0) {
            output.innerHTML = `<p><strong>The player does not have registered events.</strong></p>`;
            return;
        }

        const items = history.eventTypes.map(e => `<li><strong>${e}</strong></li>`).join("");
        output.innerHTML = `
            <h3 style="color: green;">Event History - ${history.playerNickname}</h3>
            <ul style="padding-left: 20px;">${items}</ul>
        `;
    } catch (err) {
        console.error("Error loading events:", err);
        document.getElementById('output').innerHTML = `<p style="color: red;"><strong>Error loading player events.</strong></p>`;
    }
}

