<template>
        <Card class="treatment" title="Traitement - Listes des traitements">
            <template v-slot:main>
                <div class="informationTreatment">
                <!-- <div class="treatmentRow" v-for="treatment in jsonTreatment" :key="treatment.id"> -->
                        <!-- <p>{{ treatment.nom }}</p> -->
                        <!-- <p>{{ treatment.stade_phenologique }}</p> -->
                        <!-- <Pellet :label="`${treatment.nom}`" /> -->
                    </div>
                <!-- </div> -->
            </template>
        </Card>
        <Card class="workers" title="Ouvriers - Listes des ouvriers">
            <template v-slot:main>           
                <div class="informationTable" v-if="allWorkers > []">
                    <div class="informationRow" v-for="worker in allWorkers.listeOuvriers" :key="worker.id">
                        <div>
                            <p>{{ worker.id }}</p>
                        </div>
                        <div>
                            <p>{{ worker.nom }}</p>                        
                        </div>
                        <div>
                            <p>{{ worker.prenom }}</p>                        
                        </div>
                        <div>
                            <p>{{ worker.telephone }}</p>                        
                        </div>
                    </div>
                </div>
            </template>
        </Card>
        <Card class="field" title="Champs - Listes des champs">
            <template v-slot:main>           
                <div class="informationTable">
                    <div class="informationRow" v-for="(field, index) in allField" :key="field.id">
                        <div>
                            <p>{{ index }}</p>
                        </div>
                        <div>
                            <p>{{ field.label }}</p>
                        </div>
                        <div>
                            <p>{{ field.surface }}</p>
                        </div>
                        <div>
                            <p>{{ field.id_type_de_sol }}</p>
                        </div>
                    </div>
                </div>
            </template>
        </Card>
        <Card class="weather" title="Météo - Température de la journée">
            <template v-slot:main>                
                <apexchart class="weather" height="100%" type="line" :options="options" :series="series"></apexchart>
            </template>
        </Card>
        <Card class="domains" title="Domaines - Information du domaine">
            <template v-slot:main>
                <div class="informationDomain">
                    <div></div>
                    <div>
                        <!-- <p>{{ jsonDomain.label }}</p>
                        <p>{{ jsonDomain.code_postal }}</p>
                        <p>{{ jsonDomain.rue }}</p> -->
                    </div>
                </div>  
            </template>
        </Card>
</template>

<script setup>
    import { ref } from 'vue';
    import Card from '../components/Card.vue';
    import Pellet from '../components/Pellet.vue';
    import {getAllOwners, getAllUsers, getAllWorkers} from '../services/userServices.js';
    import { getAllField } from '@/services/fieldServices';
    
    const series = ref([
        {
            name: "Températures",
            data: ['30', '31', '28', '26', '34']
        }
    ]); 

    const allWorkers = ref([]);

    getAllWorkers(JSON.parse(atob(document.cookie.split('.')[1])).id).then(
        (response) => {
            allWorkers.value = response;
        }
    );

    const allField = ref();

    getAllField(JSON.parse(atob(document.cookie.split('.')[1])).id).then(
        (response) => {
            allField.value = response;
        }
    );

    const options = ref({
        xaxis: {
            type : 'numeric'
        },
        colors:['#4ca851']
    });    

</script>

<style scoped>

    .treatment
    {
        grid-column: 2 / 48;
        grid-row: 2 / 12;
    }

    .workers 
    {
        grid-column: 2 / 24;
        grid-row: 13 / 31;
    }

    .field 
    {
        grid-column: 25 / 48;
        grid-row: 13 / 31;
    }

    .weather 
    {
        grid-column: 2 / 30;
        grid-row: 32 / 50;
    }

    .domains 
    {
        grid-column: 31 / 48;
        grid-row: 32 / 50;
    }

    .informationTreatment 
    {
        width: 95%;
        height: 100%;

        display: grid;
        grid-template-columns: repeat(3, 1fr);
        grid-template-rows: repeat(6, 1fr);

        overflow: scroll;

    }

    .informationTreatment .treatmentRow 
    {
        display: flex;
        align-items: center;
        justify-content: flex-start;


        gap: 10px;
    }

    .informationTreatment .treatmentRow p
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

    .weather 
    {
        width: 100%;

        overflow: hidden;
    }

    .informationDomain 
    {
        height: 100%;
        width: 100%;

        display: flex;
        align-items: center;
        justify-content: center;

        gap: 30px;
    }

    .informationDomain > :nth-child(1) 
    {
        width: 180px;
        height: 180px;
        border-radius: 360px;


        background-image: url('../public/images/test.jpg');
        background-position: center;
        background-size: cover;

    }

    .informationDomain > :nth-child(2) 
    {
        width: 200px;
        height: 200px;

        display: flex;
        flex-direction: column;
        align-items: flex-start;
        justify-content: center;

    } 

    .informationDomain > :nth-child(2) 
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

    }

    
</style>