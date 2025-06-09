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
                        <a href="#" class="editButton" @click="addHandleClick(music.id, playList )">Partager</a>
                    </div>
                </div>
            </div>
        </template>
    </Card>
</template>

<script setup>
    import { ref, onMounted } from 'vue'
    import Card from '../components/Card.vue'
    import { getAllMusics } from '../services/musicsServices.js'

    const allMusics = ref([]);    

    getAllMusics().then((response) => {
        allMusics.value = response;
    });

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
