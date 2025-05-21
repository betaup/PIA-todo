let workMinutes = 25;
let breakMinutes = 5;
let seconds = 0;
let isRunning = false;
let interval;

function setTimer(work, rest) {
    clearInterval(interval);
    workMinutes = work;
    breakMinutes = rest;
    seconds = 0;
    isRunning = false;
    updateDisplay(workMinutes, seconds);
}

function updateDisplay(min, sec) {
    const timer = document.getElementById('timer');
    timer.textContent = `${String(min).padStart(2, '0')}:${String(sec).padStart(2, '0')}`;
}

function startTimer() {
    if (isRunning) return;
    isRunning = true;
    let totalSeconds = workMinutes * 60;

    interval = setInterval(() => {
        if (totalSeconds <= 0) {
            clearInterval(interval);
            alert("¡Tiempo de descanso!");
            setTimer(breakMinutes, 0);
            startTimer(); // ← inicia el descanso automaticamente
            return;
        }
        totalSeconds--;
        const min = Math.floor(totalSeconds / 60);
        const sec = totalSeconds % 60;
        updateDisplay(min, sec);
    }, 1000);
}

function resetTimer() {
    clearInterval(interval);
    isRunning = false;
    updateDisplay(workMinutes, 0);
}

// Clima con OpenWeatherMap
const apiKey = '8a7d87cb965e278904e4a8d5bce07a46';
const ciudad = 'Monterrey';
fetch(`https://api.openweathermap.org/data/2.5/weather?q=${ciudad}&appid=${apiKey}&units=metric&lang=es`)
    .then(response => response.json())
    .then(data => {
        const clima = `${data.name}: ${data.main.temp}°C, ${data.weather[0].description}`;
        document.getElementById("weather").innerText = clima;
    })
    .catch(err => {
        document.getElementById("weather").innerText = "No se pudo cargar el clima.";
    });
