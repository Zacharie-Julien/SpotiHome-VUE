<template>
    <Card class="music" v-if="currentMusicName" :title="`${musiName}`">   
        <template v-slot:main>
            <audio controls>
                <source :src="`http://localhost:8080/spotiHome/webresources/music/getMusic/${currentMusicName}`" type="audio/mpeg"/>
            </audio>
        </template>
    </Card>
</template>

<script setup>

    import Card from '../components/Card.vue'
    import { ref, onMounted } from 'vue'
    import { useRoute } from 'vue-router'
    import { getSharedMusic } from '../services/musicsServices'

    const route = useRoute();
    const currentMusicName = ref(false);
    const musiName = ref(route.params.musicName);

    getSharedMusic(route.params.idAuditeur, route.params.idMusic, route.params.dateShare).then((response) => {        
        currentMusicName.value = response;
    })  
    
</script>

<style scoped>

    .music 
    {
        grid-row: 1 / 48;
        grid-column: 1 / 48;
    }

</style>

