<template>
    <header>
        <slot name="header"></slot>
    </header>
    <main>
        <slot name="main"></slot>
    </main>
    <footer>
        <slot name="footer"></slot>
    </footer>
</template>

<script setup>

</script>


<style>

    #app 
    {
        padding: 0;
        margin: 0;
        width: 100%;
        height: 100vh;

        display: grid;
        grid-template-columns: repeat(6, 1fr);
        grid-template-rows: repeat(9, 1fr);

        background-color: var(--main-black);        
    }

    header 
    {
        grid-column: 1;
        grid-row: 1 / 10;
    }

    main 
    {
        grid-column: 2/7;
        grid-row: 1 / 9;

        display: grid;
        grid-template-columns: repeat(48, 1fr);
        grid-template-rows: repeat(48, 1fr);

    }

    footer 
    {
        grid-column: 2/7;
        grid-row: 9 / 10;
    }

</style>