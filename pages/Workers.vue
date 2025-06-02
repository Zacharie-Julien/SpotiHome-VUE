<template>
    <Card class="workerList" title="Liste des ouvriers">
        <template v-slot:main>
            <div class="workerRow" v-for="(worker, index) in allWorkers.listeOuvriers" :key="worker.id">
                <div>
                    <p>{{ index }}</p>
                </div>
                <div>
                    <p>{{ worker.nom }}</p>
                </div>
                <div>
                    <p>{{ worker.prenom }}</p>
                </div>
                <div>
                    <p>{{ worker.numero }}</p>
                </div>
                <div>
                    <p>{{ worker.telephone }}</p>
                </div>
                <div>
                    <a href="#" class="deleteButton" v-on:click.prevent="handleClick(worker.id)">Supprimer</a>
                </div>
            </div>
        </template>
        
    </Card>
</template>

<script setup>

    import { ref, onMounted } from 'vue';
    import { getAllWorkers, deleteWorkerById } from '@/services/userServices';
    import Card from '../components/Card.vue'
    import Pellet from '../components/Pellet.vue'

    onMounted(() => {
        loadData();
    });

    const allWorkers = ref([]);

    function loadData(){
        getAllWorkers(JSON.parse(atob(document.cookie.split('.')[1])).id).then(
            (response) => {
                allWorkers.value = response;
            }
        );
    }

    function handleClick(id){
        deleteWorkerById(id).then(()=>{
            setTimeout(() => {
                loadData();
            }, 50);
        });
    }

</script>

<style scoped>

    .workerList 
    {
        grid-row: 2 / 49;
        grid-column: 2 / 48;
    }

    .workerRow 
    {
        width: 95%;
        height: 40px;

        display: grid;
        grid-template-columns: repeat(6, 1fr);
        grid-template-rows: 1;

    }

    .workerRow > div 
    {

        display: flex;
        align-items: center;
        justify-content: flex-start;

        border-bottom: solid 1px var(--main-border-gray);
    }

    .workerRow > div > a, p
    {
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

        margin-left: 20px;

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