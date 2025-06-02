
<template>
    <Card class="picture" title="Image">
        <template class="custom" v-slot:main>
            <div></div>
        </template>
    </Card>
    <Card class="info" title="Information">
        <template v-slot:main>            
        </template>        
    </Card>
    
    <Card class="action" title="Dernière actions éffectué sur le domaine">
        <template v-slot:main>            
        </template>        
    </Card>
</template>

<script setup>
    import Card from '../components/Card.vue'
</script>

<style scoped>

    .picture
    {        
        grid-column: 2 / 12;
        grid-row: 2 / 16;

    }
        .picture div 
        {
            width: 200px;
            height: 200px;

            border-radius: 360px;
            background-color: blue;
        }

    .info 
    {
        grid-column: 2 / 12;
        grid-row: 17 / 48;
    }

    .action
    {
        grid-column: 13 / 48;
        grid-row: 2 / 48;
    }

</style>    