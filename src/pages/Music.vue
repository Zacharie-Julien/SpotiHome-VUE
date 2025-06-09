<template>
    <Card class="musics" title="Modification musique">
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
                        <a href="#" class="editButton" @click="changeDisplay(music.label, music.id)">Modifier</a>
                    </div>
                </div>
            </div>
        </template>
    </Card>

    <div class="board" v-if="displayBoolean">
        <Card title="Modification musique" class="test">
            <template v-slot:main>
                <form class="addPlaylist" @submit.prevent="updateHandlePlaylist(newMusicName, currentMusicModifId)">
                    <input type="text" v-model="newMusicName" :placeholder="`${currentMusicModifName}`">
                    <button type="submit">Modifier</button>
                </form>    
            </template>            
        </Card>
    </div>
</template>

<script setup>
    
    import { ref, onMounted } from 'vue'
    import Card from '../components/Card.vue'
    import { getAllMusics } from '../services/musicsServices.js'

    const allMusics = ref([]);    
    const displayBoolean = ref(false);
    const currentMusicModifName = ref(); 
    const currentMusicModifId = ref(); 

    getAllMusics().then((response) => {
        allMusics.value = response;
    });

    function changeDisplay(musicName, musicId){
        displayBoolean.value = true;
        currentMusicModifName.value = musicName;
        currentMusicModifId.value = musicId;
    }

    function updateHandlePlaylist(label, id){
        updatePlaylist(label, id).then(()=>{
            router.go(0);
        });
    }

</script>

<style scoped>

    .musics
    {
        grid-column: 1 / 49;
        grid-row: 1 / 49;
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

    .board
    {
        display: grid;
        grid-template-columns: repeat(9, 1fr);
        grid-template-rows: repeat(9, 1fr);

        position: absolute;
        top: 0;
        left: 0;
        width: 100vw;
        height: 100vh;

        background-color: rgba(0, 0, 0, .6);
    }

    .board > .test
    {
        grid-row: 2 / 8;
        grid-column: 4 / 7;

        
    }

    .addPlaylist 
    {
        width: 100%;
        height: 100%;

        display: flex;
        align-items: center;
        justify-content: center;
    }

</style>