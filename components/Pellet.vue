<template>
    <p>{{ label }}</p>
</template>

<script setup>

    defineProps({
        label : String
    });

</script>

<style scoped>

    p 
    {
        display: flex;
        align-items: center;
        justify-content: center;

        border-radius: 30px;
        width: auto;

        background-color: #3D90D7;

        padding-left: 10px;
        padding-right: 10px;

        color: white;
        text-decoration: none;
        list-style: none;
        font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
        font-optical-sizing: auto;
        font-weight: 100;
        font-style: normal;
        font-size: 13px !important;
        word-spacing: 1px;
        transition-duration: .125s;

        text-transform: uppercase;

    }

</style>