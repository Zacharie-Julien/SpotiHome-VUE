<template>
    <Card class="type" title="Admin - Type">
        <template v-slot:main>           
            <div class="informationTable" v-if="allType > []">
                <div class="informationRow" v-for="(type, index) in allType" :key="type.id">
                    <div>
                        <p>{{ index }}</p>
                    </div>
                    <div>
                        <p>{{ type.label }}</p>                        
                    </div>
                    <div></div>
                    <div></div>
                </div>
            </div>
        </template>
    </Card>
    <Card class="genre" title="Admin - Genre">
         <template v-slot:main>           
            <div class="informationTable" v-if="allGenre > []">
                <div class="informationRow" v-for="(genre, index) in allGenre" :key="genre.id">
                    <div>
                        <p>{{ index }}</p>
                    </div>
                    <div>
                        <p>{{ genre.label }}</p>                        
                    </div>
                    <div></div>
                    <div></div>
                </div>
            </div>
        </template>
    </Card>
    <Card class="addMusique" title="Admin - Add Music">
        <template v-slot:main>           
            <form @submit.prevent="handleAddMusic(labelMusique, labelAutor, idType, idGenre)">
                <div class="informationTable">
                    <div class="informationRow">
                        <div>
                            <label for="file">
                                <span class="fileInputText">Choisir un audio</span>
                                <input type="file" class="fileInput" id="file" @change="handleFileChange">
                            </label>
                        </div>
                        <div>
                            <input type="text" placeholder="Insérer un Label" v-model="labelMusique">
                        </div>
                    </div>
                </div>
                <div class="informationTable">
                    <div class="informationRow">
                        <div>
                            <select class="optionSelect" v-model="idType">
                                <option value="" selected disabled hidden>Choisir un type</option>
                                <option v-for="type in allType" :value="`${type.id}`">{{ type.label }}</option>
                            </select>
                        </div>
                        <div>
                            <select class="optionSelect" v-model="idGenre">
                                <option value="" selected disabled hidden>Choisir un genre</option>
                                <option v-for="genre in allGenre" :value="`${genre.id}`">{{ genre.label }}</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="informationTable">
                    <div class="informationRow">
                        <div>
                            <input type="text" v-model="labelAutor" placeholder="Insérer le label d'un auteur">
                        </div>
                        <div>
                            <button type="submit">Ajouter</button>
                        </div>
                    </div>
                </div>
            </form>            
        </template>
    </Card>
</template>

<script setup>

    import Card from '../components/Card.vue'
    import { ref, onMounted } from 'vue'
    import { getAllType } from '../services/typeServices.js'
    import { getAllGenre } from '../services/genreServices.js'
    import { addMusic, uploadMusicFile } from '@/services/musicsServices'
    import { useRouter } from 'vue-router'

    const router = useRouter();
    const allType = ref([]);
    const allGenre = ref([]);
    const file = ref('');

    const handleFileChange = (event) => {
        file.value = event.target.files[0];
    }

    getAllType().then((response) => {
        allType.value = response;
    });    

    getAllGenre().then((response) => {
        allGenre.value = response;
    });

    function handleAddMusic(labelMusique, labelAutor, idType, idGenre){
        uploadMusicFile(file.value).then(() => {
            addMusic(file.value.name, JSON.parse(atob(document.cookie.split('.')[1])).id, labelMusique, labelAutor, idType, idGenre).then(() => {
                router.go(0);
            });        
        });        
    }

</script>

<style scoped>

    .type
    {
        grid-row: 1 / 24;
        grid-column: 1 / 24;
    }

    .genre 
    {
        grid-row: 1 / 24;
        grid-column: 24/ 49;
    }

    .addMusique 
    {
        grid-row: 24 / 49;
        grid-column: 1 / 49;
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
        grid-template-columns: repeat(2, 1fr);

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
        width: 100%;
    }

    .informationRow > div p 
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

    .optionSelect, .fileInputText
    {
        background-color: transparent;
        border: none;
        min-width: 250px;

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
</style>