<template>
     <Card class="musics" title="Partager une musique">
        <template v-slot:main>
            <div class="informationTable" v-if="allMusics > []">
                <div class="informationRow" v-for="(music, index) in allMusics" :key="music.id">
                    <div>
                        <p>{{ index }}</p>
                    </div>
                    <div>
                        <p>{{ music.label }}</p>                        
                    </div>
                    <div></div>
                    <div>
                        <a href="#" class="editButton" @click="handleAdd(music.id, music.label,)">Partager</a>
                    </div>
                </div>
            </div>
        </template>
    </Card>
</template>

<script setup>
    import { ref, onMounted } from 'vue'
    import Card from '../components/Card.vue'
    import { getAllMusics, addSharedMusic } from '../services/musicsServices.js'

    const allMusics = ref([]);    

    getAllMusics().then((response) => {
        allMusics.value = response;
    });

    function handleAdd(idMusic, musicName){
        copierDansPressePapier(idMusic, musicName);
        addSharedMusic(JSON.parse(atob(document.cookie.split('.')[1])).id, idMusic);

    }

    function copierDansPressePapier(idMusic, musicName) {
        const now = new Date();

        const formattedDate = `${now.getFullYear()}-${(now.getMonth() + 1)
        .toString()
        .padStart(2, '0')}-${now.getDate()
        .toString()
        .padStart(2, '0')} ${now.getHours()
        .toString()
        .padStart(2, '0')}:${now.getMinutes()
        .toString()
        .padStart(2, '0')}:${now.getSeconds()
        .toString()
        .padStart(2, '0')}`;

        navigator.clipboard.writeText('http://localhost:5173/shared/'+ musicName +'/'+ JSON.parse(atob(document.cookie.split('.')[1])).id +'/'+ idMusic +'/' + formattedDate)
        .then(() => {
            alert("Lien copié dans le presse-papiers !");
        }).catch(err => {
            console.error("Erreur de copie :", err);
            alert("Échec de la copie.");
        });
    }

</script>

<style scoped>

    .musics
    {
        grid-column: 1 / 4;
        grid-row: 1 / 4;
    }

      .informationTable
    {
        width: 95%;
        height: 100%;

        display: flex;
        flex-direction: column;
        align-items: center;

        margin: 20px;
    }

    .informationRow 
    {
        display: grid;
        grid-template-rows: minmax(45px, 50px);
        grid-template-columns: repeat(4, 1fr);

        width: 100%;
        max-height: 50px;

    }

    .informationRow > div 
    {
        border-bottom: 1px solid var(--main-border-gray);

        display: flex;
        align-items: center;
        justify-content: flex-start;

        height: 100%;
        width: auto;
    }

    .informationRow > div p, a
    {
        margin-left: 20px;
        color: var(--main-title-gray);
        text-decoration: none;
        list-style: none;
        font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
        font-optical-sizing: auto;
        font-weight: 100;
        font-style: normal;
        font-size: 20px;
        word-spacing: 1px;
        transition-duration: .125s;

    }

    .editButton 
    {
        color: var(--main-green) !important;
    }

    .deleteButton 
    {
        color: red !important;
    }

</style>
