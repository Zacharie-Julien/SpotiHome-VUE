<template>
    <Card class="myPlaylist" title="Mes Playlists">
        <template v-slot:main>
            <div class="informationTable" v-if="allPlayLists > []">
                <div class="informationRow" v-for="(playlist, index) in allPlayLists" :key="playlist.id">
                    <div>
                        <p>{{ index }}</p>
                    </div>
                    <div>
                        <p>{{ playlist.label }}</p>                        
                    </div>
                    <div></div>
                    <div>
                        <a href="#" class="deleteButton" @click="deleteHandleClick(playlist.id)">Supprimer</a>
                        <a href="#" class="editButton" @click="changeDisplay(playlist.label, playlist.id)">Modifier</a>
                        <a href="#" class="editButton" @click="goToPageB(playlist.id)">Voir</a>
                    </div>
                </div>
            </div>
        </template>
    </Card>
    <Card class="addMusic" title="Ajouter une music à une playlist">
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
                        <select v-model="playList">
                            <option v-for="playlist in allPlayLists" :value="`${playlist.id}`">{{ playlist.label }}</option>
                        </select>
                        <a href="#" class="editButton" @click="addHandleClick(music.id, playList )">Ajouter</a>
                    </div>
                </div>
            </div>
        </template>
    </Card>
    <Card class="addPlayList" title="Crée une playList">
        <template v-slot:main>
            <form class="addPlaylist" @submit.prevent="addHandlePlaylist(playlistName, idAuditor)">
                <input type="text" v-model="playlistName" placeholder="Ajouter une playlist">
                <button type="submit">Ajouter</button>
            </form>
        </template>        
    </Card>
    <Card class="countPlaylist" title="Nombres de Playlist">
        <template v-slot:main>
            <p class="countText">
                {{ allPlayLists.length }}
            </p>            
        </template>        
    </Card>

    <div class="board" v-if="displayBoolean">
        <Card title="Modification de playlist" class="test">
            <template v-slot:main>
                <form class="addPlaylist" @submit.prevent="updateHandlePlaylist(newPlaylistName, currentPlaylistModifId)">
                    <input type="text" v-model="newPlaylistName" :placeholder="`${currentPlaylistModifName}`">
                    <button type="submit">Modifier</button>
                </form>    
            </template>            
        </Card>
    </div>
</template>

<script setup>
    import { ref, onMounted } from 'vue'
    import { useRouter } from 'vue-router'
    import Card from '../components/Card.vue'
    import { getPlayListById, deletePlayListById, addMusicToPlaylist, addPlaylist, updatePlaylist} from '../services/playListServices'
    import { getAllMusics } from '../services/musicsServices.js'


    const allPlayLists = ref([]);    
    const router = useRouter();
    const allMusics = ref([]);    
    const idAuditor = JSON.parse(atob(document.cookie.split('.')[1])).id; 
    const displayBoolean = ref(false);
    const currentPlaylistModifName = ref(); 
    const currentPlaylistModifId = ref(); 


    function goToPageB(idPlayList){
        router.push({ name: 'viewPlaylist', params: { id: idPlayList } })
    }

    getPlayListById(JSON.parse(atob(document.cookie.split('.')[1])).id).then((response) => {
        allPlayLists.value = response;
    });

    function changeDisplay(newLabel, id){
        displayBoolean.value = true;
        currentPlaylistModifName.value = newLabel;
        currentPlaylistModifId.value = id;
    }

    function addHandlePlaylist(playlistName, idAuditor){
        addPlaylist(playlistName, idAuditor).then(() =>{
            router.go(0);
        });
    }

    function deleteHandleClick(id){
        deletePlayListById(id).then(() => {
            router.go(0);
        });
    }

    function addHandleClick(id_music, id_playlist){
        addMusicToPlaylist(id_music, id_playlist).then(() => {
            router.go(0);
        });
    }

    function updateHandlePlaylist(label, id){
        updatePlaylist(label, id).then(()=>{
            router.go(0);
        });
    }

    getAllMusics().then((response) => {
        allMusics.value = response;
    });
</script>

<style scoped>

    .myPlaylist
    {
        grid-column: 1 / 4;
        grid-row: 1 / 2;
        overflow-y: auto;
    }

    .addMusic
    {
        grid-column: 1 / 3;
        grid-row: 2 / 4;
    }

    .addPlayList
    {
        grid-column: 3 / 4;
        grid-row: 2 / 3;        
    }

    .countPlaylist
    {
        grid-column: 3 / 4;
        grid-row: 3 / 4;
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

    select 
    {   
        background-color: transparent;
        border: none;
        border-bottom: solid .1px var(--main-title-gray);
        color: var(--main-title-gray);
    }

    .countText 
    {
        color: var(--main-green);
        font-size: 13em;
        font-weight: 900;
    }

    .addPlaylist
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