<template>
    <Card class="music" v-for="music in allMusics" :key="music.id" :title="`${music.label} - ${music.auteur}`">   
        <template v-slot:main>
            <audio controls>
                <source :src="`http://localhost:8080/spotiHome/webresources/music/getMusic/${music.lien_audio}`" type="audio/mpeg"/>
            </audio>
        </template>
    </Card>
</template>

<script setup>

    import { ref, onMounted } from 'vue'
    import Card from '../components/Card.vue'
    import { getAllMusics } from '../services/musicsServices.js'

    const allMusics = ref([]);

    async function musicExist(musicName) {
        if (!musicName) return false;

        const encodedFileName = encodeURIComponent(musicName);
        try {
            const response = await fetch(`http://localhost:8080/spotiHome/webresources/music/getMusic/${encodedFileName}`, {
                method: 'HEAD'
            });
            return response.status !== 404;
        } catch (error) {
            return false;
        }
    }

    async function loadValidMusics() {
        const musics = await getAllMusics();
        const validMusics = [];

        for (const music of musics) {
            if (await musicExist(music.lien_audio)) {
                validMusics.push(music);
            }
        }

        allMusics.value = validMusics;
    }

    onMounted(() => {
        loadValidMusics();
    });
</script>

<style scoped>

    .music 
    {
        height: 300px;
    }

</style>