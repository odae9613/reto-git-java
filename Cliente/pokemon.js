const BASE_URL = "https://pokeapi.co/api/v2/pokemon/";
const pokemonInput = document.getElementById("inputNombre");
const result = document.getElementById("resultado");
const mensaje = document.getElementById("mensajeError");
const boton = document.getElementById("buscarPokemon");

function clearResult() {
    result.innerHTML="";
}

function validarNombre() {
    const nombre = pokemonInput.value.trim().toLowerCase();
    if(!nombre) {
        return {ok: false, mensaje: "Introduce un pokemon"};
    }
    return {ok: true, nombre};
}

function setErrorMessage(msn) {
    mensaje.textContent = msn;
}

function render(pokemon) {
    result.innerHTML = `
    <p>Nombre: ${pokemon.name}</p>
    <p>Altura: ${pokemon.height}</p>
    <p>Peso: ${pokemon.weight}</p>
    <img src="${pokemon.sprites.front_default}" alt="${pokemon.name}">
    `;
}

async function fetchJson(url) {
    const res = await fetch(url);
    if(!res.ok) {
        throw new Error("HTTP");
    }
    return await res.json();
}

async function llamadaJson() {
    clearResult();
    setErrorMessage("");
    const pokemonValidado = validarNombre();
    if(!pokemonValidado.ok) {
        setErrorMessage("Introduce un pokemon correcto.");
        return;
    }
    const url = `${BASE_URL}${pokemonValidado.nombre}`;
    try {
        const data = await fetchJson(url);
        render(data);
        setErrorMessage("OK");
    } catch(error) {
        setErrorMessage("Pokemon no encontrado");
    }
}

boton.addEventListener("click", llamadaJson);