<template>
    <Card class="genreList" title="Gestion des genres">
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
                    <div>
                        <a href="#" class="deleteButton" @click="deleteHandleClick(genre.id)">Supprimer</a>
                        <a href="#" class="editButton" @click="changeDisplay(genre.label, genre.id)">Modifier</a>
                    </div>
                </div>
            </div>
        </template>
    </Card>
    <Card class="genreAdd" title="Ajout des genres">
        <template v-slot:main>
            <form class="genreAddD" @submit.prevent="addHandleClick(newGenre)">
                <input type="text" v-model="newGenre" required placeholder="Entré votre nouveau GENRE">
                <button type="submit">Ajouter</button>
            </form>
        </template>
    </Card>
    <Card class="genreCount" title="Nombre de genre">
        <template v-slot:main>
            <p class="countText">
                {{ allGenre.length }}
            </p>
        </template>
    </Card>

    <div class="board" v-if="displayBoolean">
        <Card title="Modification de playlist" class="test">
            <template v-slot:main>
                <form class="addPlaylist" @submit.prevent="updateHandleGenre(newGenreName, currentGenreModifId)">
                    <input type="text" v-model="newGenreName" :placeholder="`${currentGenreModifName}`">
                    <button type="submit">Modifier</button>
                </form>    
            </template>            
        </Card>
    </div>
</template>

<script setup>

    import { ref, onMounted } from 'vue'
    import { useRouter } from 'vue-router'
    import { getAllGenre, deleteGenreById, addGenre, upDateGenre } from '../../services/genreServices'
    import Card from '../../components/Card.vue';

    const allGenre = ref([]);
    const router = useRouter();
    const displayBoolean = ref(false);

    const currentGenreModifName = ref(); 
    const currentGenreModifId = ref(); 

    getAllGenre().then((response) => {
        allGenre.value = response;
    });

    function changeDisplay(newLabel, id){
        displayBoolean.value = true;
        currentGenreModifName.value = newLabel;
        currentGenreModifId.value = id;
    }

    function addHandleClick(label){
        addGenre(label).then(() => {
            router.go(0);
        });
    }

    function deleteHandleClick(id){
        deleteGenreById(id).then(() => {
            router.go(0);
        });
    }

    function updateHandleGenre(label, id){
        upDateGenre(label, id).then(()=>{
            router.go(0);
        });
    }

</script>

<style scoped>

    .genreList 
    {
        grid-row: 1 / 28;
        grid-column: 1 / 49;
    }

    .genreAdd 
    {
        grid-row: 28 / 49;
        grid-column: 1 / 24;
    }
    
    .genreCount
    {
        grid-row: 28 / 49;
        grid-column: 24 / 49;
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
        color: rgba(236, 106, 34) !important;
    }

    .countText 
    {
        color: var(--main-green);
        font-size: 15em;
        font-weight: 900;
    }

    .genreAddD 
    {
        width: 100%;
        height: 100%;

        display: flex;
        align-items: center;
        justify-content: center;
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

</style>