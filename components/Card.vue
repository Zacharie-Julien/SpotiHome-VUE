<template>
    <div class="globalDiv">
        <div class="topCard">
            <p>{{ title }}</p>
        </div>
        <div class="mainCard">
            <slot name="main"></slot>
        </div>
    </div>
</template>

<script setup>

    defineProps({
        title : String
    });

</script>

<style scoped>

    .globalDiv 
    {
        display: grid;
        grid-template-rows: 40px 1fr;
        grid-template-columns: 1fr;
        border: solid 1px var(--main-border-gray);

        background-color: var(--main-gray);
    }

    .topCard 
    {
        grid-row: 1;

        display: flex;
        align-items: center;
        justify-content: flex-start;
    }
        .topCard p
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

            margin-left: 15px;
        }
    
        .mainCard 
    {
        grid-row: 2;

        display: flex; 
        flex-direction: column;
        align-items: center;
        justify-content: flex-start;

        overflow-y: auto;
        overflow-x: hidden;
    }

</style>