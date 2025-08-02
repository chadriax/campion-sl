## 🔄 Flujo de interacción entre microservicios

### 🧾 1. Usuario crea una apuesta

- **Apuestas** → `POST /apuestas` _(owner: rowmi)_  
  (recibe ID de partida y de usuario)

- Llama internamente a:
  - `GET /usuarios/{id}` _(owner: alpeta)_ → para validar usuario
  - `GET /partidas/{id}` _(owner: adri)_ → para validar que la partida existe

---

### 🏁 2. Partida finaliza

- **Partidas** → `PUT /partidas/{id}/resultado` _(owner: adri)_
- Publica en cola: `resultado-partida`

---

### 🎯 3. Apuestas procesa resultado

- Escucha la cola `resultado-partida`
- Calcula ganancias o pérdidas
- Publica evento `monedas-actualizadas` con:
  - ID de usuario
  - Cantidad de monedas a modificar

---

### 💰 4. Usuarios actualiza saldo

- Escucha la cola `monedas-actualizadas`
- Realiza `PUT /usuarios/monedas` _(owner: alpeta)_ para actualizar monedas del usuario


### Puertos microservicios

- Usuarios (Alpeta)  8081
- Partidas (Adri)  8082
- Apuestas (Rowmi)  8083
