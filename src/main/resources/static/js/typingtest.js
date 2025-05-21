let fraseActual = '';

// Cargar una frase aleatoria de 15 palabras desde la API
async function cargarFraseAleatoria() {
    try {
        const respuesta = await fetch("https://random-word-api.vercel.app/api?words=15");
        const palabras = await respuesta.json();
        fraseActual = palabras.join(' ');
        document.getElementById('texto-para-teclear').textContent = fraseActual;

        // Limpiar textarea y resultado
        document.querySelector('textarea').value = '';
        const resultado = document.getElementById('resultado');
        resultado.textContent = '';
        resultado.classList.remove('text-success', 'text-danger');
        document.getElementById('boton-reset').classList.add('d-none');
    } catch (error) {
        document.getElementById('texto-para-teclear').textContent = "⚠️ Error al cargar la frase.";
        console.error("Error al obtener palabras:", error);
    }
}

// Verificar si el texto escrito es correcto
function verificarEscritura() {
    const entrada = document.querySelector('textarea').value.trim();
    const resultado = document.getElementById('resultado');
    const botonReset = document.getElementById('boton-reset');

    if (entrada === fraseActual) {
        resultado.textContent = "✅ ¡Bien hecho!";
        resultado.classList.remove('text-danger');
        resultado.classList.add('text-success');
        botonReset.classList.remove('d-none');
    } else if (fraseActual.startsWith(entrada)) {
        resultado.textContent = "";
        resultado.classList.remove('text-success', 'text-danger');
        botonReset.classList.add('d-none');
    } else {
        resultado.textContent = "❌ Incorrecto";
        resultado.classList.remove('text-success');
        resultado.classList.add('text-danger');
        botonReset.classList.add('d-none');
    }
}

// Cargar una frase al iniciar la página
window.addEventListener('DOMContentLoaded', cargarFraseAleatoria);
