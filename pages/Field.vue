<template>
    <Card class="fieldList" title="Liste des champs">
        <template v-slot:main>
            <form class="fieldRow" @submit.prevent="updateHandleClick(field.id, fieldName, fieldSurfaces, fieldType)" v-for="(field, index) in allField" :key="field.id">
                <div>
                    <p>{{ index }}</p>
                </div>
                <div>
                    <input v-model="fieldName" type="text" :placeholder="`${field.label}`" required>
                </div>
                <div>
                    <input v-model="fieldSurfaces" type="number" :placeholder="`${field.surface}`" required>
                </div>
                <div>
                    <select v-model="fieldType">
                        <option v-for="groundType in allGroundType" :value="`${groundType.id}`">{{ groundType.label }}</option>
                    </select>
                </div>
                <div>
                    <p>{{ field.typeSol.pH }}</p>
                </div>
                <div>
                    <a href="#" class="deleteButton" @click.prevent="deleteHandleClick(field.id)" @click="show()">Supprimer</a>
                    <button class="editButton" type="submit">Modifier</button>
                </div>
            </form>
            <Toast />
        </template>
    </Card>
</template>

<script setup>

    import { ref, onMounted } from 'vue'
    import { useRouter } from 'vue-router'
    import { getAllField, deleteFieldById, getAllGroundType, updateField } from '../services/fieldServices'
    import Card from '../components/Card.vue'
    import Toast from 'primevue/toast'
    import Button from "primevue/button"

    import { useToast } from 'primevue/usetoast'

    const toast = useToast()
    const allField = ref([]);   
    const allGroundType = ref([]);
    const router = useRouter();
    


    onMounted(() => {
        getAllGroundType().then((response) => {
            allGroundType.value = response;  
        });            
        getAllField(JSON.parse(atob(document.cookie.split('.')[1])).id).then(
            (response) => {
                allField.value = response;
            }
        );
    });

    function show() {
        toast.add({ severity: 'success', summary: 'Succès', detail: 'Message affiché', life: 3000 })
    }

    function deleteHandleClick(id){
        deleteFieldById(id).then(() => {
            router.go(0);
        });
    }

    function updateHandleClick(id, label, surface, type){
        updateField(id, label, surface, type).then(() => {
            router.go(0);
        });
    }

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

    .fieldRow > div > a, p
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

    .fieldRow input, button 
    {
        background-color: transparent;
        border: none;

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

    .fieldRow input:focus 
    {
        outline: none;
        border: none;
    }

    .fieldRow button, select:hover 
    {
        cursor: pointer;
    }

</style>
