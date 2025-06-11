<template>
  <Card title="Erreur" class="error">
    <template v-slot:main>
        <div class="board">
            <h1>
                Erreur 401: Unauthorized
            </h1>
            <p>
                Vous n'êtes pas autorisé à accéder à cette ressource.
            </p>
        </div>
    </template>
  </Card>
</template>

<script setup>
    import Card from '../../components/Card.vue'

</script>

<style scoped>

    .error
    {
        grid-row: 1 / 48;        
        grid-column: 1 / 48;
    }

    .board 
    {
        width: 100%;
        height: 100%;

        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
    }
    
    .board > h1, p
    {
        font-size: 10em;

        color: var(--main-green);
        text-decoration: none;
        list-style: none;
        font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
        font-optical-sizing: auto;
        font-weight: 100;
        font-style: normal;
        word-spacing: 1px;
        transition-duration: .125s;

        margin-left: 20px;
    }
    .board > p
    {
        font-size: 4em;
        color: var(--main-title-gray);
        margin-left: 20px;
    }

</style>