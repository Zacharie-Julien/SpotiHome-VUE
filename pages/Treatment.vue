<template>
    <Card class="fieldList" title="Liste des traitements">
        <template v-slot:main>
            <div class="fieldRow" v-for="operation in allOperation" :key="operation.id">
                <div>
                    <p>{{ operation.champ.label }}</p>
                </div>
                <div>                    
                    <p>{{ operation.traitement.produit.label }}</p>
                </div>
                <div>
                    <p>{{ operation.traitement.produit.dre }}</p>
                </div>
                <div>
                    <p>{{ operation.ouvrier.nom }}</p>
                </div>
                <div>
                    <p>{{ operation.ouvrier.prenom }}</p>
                </div>
                <div>
                    <p class="deleteButton">Supprimer</p>
                    <p class="editButton">Modifier</p>
                </div>
            </div>
        </template>
        
    </Card>
</template>

<script setup>

    import { ref } from 'vue'
    import Card from '../components/Card.vue'
    import { getAllOperationById } from '@/services/operationServices'
    import Pellet from '../components/Pellet.vue'

    const allOperation = ref();

    getAllOperationById(JSON.parse(atob(document.cookie.split('.')[1])).id).then(
        (response) => {
            allOperation.value = response;  
        }
    );
            
</script>

<style scoped>

    .fieldList 
    {
        grid-row: 2 / 49;
        grid-column: 2 / 48;
    }

    .fieldRow 
    {
        width: 95%;
        height: 40px;

        display: grid;
        grid-template-columns: repeat(6, 1fr);
        grid-template-rows: 1;

    }

    .fieldRow > div 
    {

        display: flex;
        align-items: center;
        justify-content: flex-start;

        border-bottom: solid 1px var(--main-border-gray);
    }

    .fieldRow > div > p 
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