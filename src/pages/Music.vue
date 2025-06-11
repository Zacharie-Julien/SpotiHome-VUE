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
                        <a href="#" class="editButton" @click="changeDisplay(music.label, music.id, music.type, music.genres, music.auteur)">Modifier</a>
                        <a href="#" class="deleteButton" @click="deleteHandleMusic(music.id)">Supprimer</a>
                    </div>
                </div>
            </div>
        </template>
    </Card>

    <div class="board" v-if="displayBoolean">
        <Card title="Modification musique" class="test">
            <template v-slot:main>
                <form class="modifMusic" @submit.prevent="updateHandleMusic(currentMusicModifId, labelAutor, newMusicName, currentMusicModifGenre, currentMusicModifType)">
                    <input type="text" v-model="newMusicName" :placeholder="`${currentMusicModifName}`">
                    <div>
                        <select class="optionSelect" :v-model="`${currentMusicModifType}`">
                            <option v-for="type in allType" :value="`${type.id}`" selected>{{ type.label }}</option>
                        </select>
                    </div>
                    <div>
                        <select class="optionSelect" :v-model="`${currentMusicModifGenre}`">
                            <option v-for="genre in allGenre" :value="`${genre.id}`" selected>{{ genre.label }}</option>
                        </select>
                    </div>
                    <div>
                        <input type="text" v-model="labelAutor" :placeholder="`${currentMusicModifAutor}`">
                    </div>
                    <button type="submit" class="editButton">Modifier</button>                    
                </form>    
            </template>            
        </Card>
    </div>
</template>

<script setup>
    
    import { ref, onMounted } from 'vue'
    import Card from '../components/Card.vue'
    import { getAllType } from '../services/typeServices.js'
    import { getAllGenre } from '../services/genreServices.js'
    import { getAllMusics, deleteMusic, updateMusic } from '../services/musicsServices.js'
    import { useRouter } from 'vue-router'
    

    const allMusics = ref([]);    
    const displayBoolean = ref(false);
    const currentMusicModifName = ref(); 
    const currentMusicModifId = ref(); 
    const currentMusicModifType = ref(); 
    const currentMusicModifGenre = ref(); 
    const currentMusicModifAutor = ref(); 
    const router = useRouter();
    const allType = ref([]);
    const allGenre = ref([]);

    getAllMusics().then((response) => {
        allMusics.value = response;
    });

    getAllType().then((response) => {
        allType.value = response;
    });    

    getAllGenre().then((response) => {
        allGenre.value = response;
    });

    function changeDisplay(musicName, musicId, musicType, musicGenre, musicAutor){
        displayBoolean.value = true;
        currentMusicModifName.value = musicName;
        currentMusicModifId.value = musicId;
        currentMusicModifType.value = musicType;         
        currentMusicModifGenre.value = musicGenre;         
        currentMusicModifAutor.value = musicAutor; 
    }

    function updateHandleMusic(musicId, labelAutor, newMusicName, currentMusicModifGenre, currentMusicModifType){
        updateMusic(musicId, labelAutor, newMusicName, currentMusicModifGenre[0].id, currentMusicModifType.id).then(()=>{
            router.go(0);
        });
    }

    function deleteHandleMusic(id){
        deleteMusic(id).then(()=>{
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

    .modifMusic
    {
        width: 100%;
        height: 100%;

        gap: 30px;

        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
    }

    .optionSelect, .fileInputText
    {
        background-color: transparent;
        border: none;
        min-width: 300px;

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

    .fileInput[type=file]
    {
        display: none;
    }

    .fileInputText:hover
    {
        cursor: pointer;
    }

    button 
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

        background-color: transparent;
        border: none;
    }

    button:hover
    {
        cursor: pointer;
    }
</style>