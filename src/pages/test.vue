<template>
  <div>
    <input type="file" @change="onFileChange" />
    <button @click="upload">Envoyer</button>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'

const file = ref(null)

function onFileChange(event) {
  file.value = event.target.files[0]
}

async function upload() {
  if (!file.value) {
    alert("Veuillez sélectionner un fichier.")
    return
  }

  const formData = new FormData()
  formData.append("file", file.value) // ⚠️ Le nom "file" doit matcher @FormDataParam("file")

  try {
    const response = await axios.post(
      'http://localhost:8080/spotiHome/webresources/music/upload/', // adapte l'URL
      formData,
      {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }
    )
    alert(response.data)
  } catch (error) {
    console.error('Erreur lors de l’envoi du fichier :', error)
    alert("Erreur d'upload.")
  }
}
</script>