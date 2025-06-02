<template>
    <nav>
        <ul>
            <div class="topBar">
                <div></div>
            </div>
            <li class="home">
                <RouterLink to="/home">Accueil</RouterLink>
            </li>
            <li class="field">
                <RouterLink to="/field">
                    Mes champs
                </RouterLink>
            </li>
            <li class="workers">
                <RouterLink to="/workers">
                    Mes ouvriers
                </RouterLink>
            </li>
            <li class="domains">
                <RouterLink to="/domains">
                    Mon domaine
                </RouterLink>
            </li>
            <li class="treatment">
                <RouterLink to="/treatment">
                    Traitements
                </RouterLink>
            </li>
        </ul>
    </nav>

</template>

<script setup>


</script>

<style scoped>

    nav
    {

        width: 100%;
        height: 100%;

        background-color: var(--main-gray);
    }

    nav ul {
        width: 100%;
        height: 100%;

        display: grid;
        grid-template-columns: 1fr;
        grid-template-rows: repeat(30, 1fr);
    }

    nav ul li
    {
        display: flex;
        align-items: center;
        justify-content: flex-start;
        transition-duration: .125s;
    }

    nav ul li:hover
    {
        transition-duration: .125s;
        background-color: var(--main-black);
    }

    nav ul li a
    {
        text-decoration: none;
        list-style: none;
        color: var(--main-title-gray);
        font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
        font-optical-sizing: auto;
        font-weight: 100;
        font-style: normal;
        font-size: 20px;
        word-spacing: 1px;
        transition-duration: .125s;

        margin-left: 50px;
    }

    nav ul li a:hover
    {
        transition-duration: .125s;
        color: var(--main-green);
        cursor: pointer;
    }

    .nameLogo
    {
        grid-row: 1 / 3;
        grid-column: 1;

        display: flex;
        align-items: center;
        justify-content: flex-start;

        background-color: var(--main-green);
    }
        .nameLogo p
        {
            margin-left: 30px;

            font-family: 'Courier New', Courier, monospace;
            font-weight: 900;
            font-size: 3em;
        }

    .topBar
    {
        grid-row: 9;
        grid-column: 1;

        display: flex;
        align-items: center;
        justify-content: center;

    }

        .topBar > div
        {
            height: 2px;
            width: 87%;
            border-radius: 2px;

            background-color: var(--main-green);
        }

    .home
    {
        grid-row: 10;
        grid-column: 1;
    }

    .field
    {
        grid-row: 11;
        grid-column: 1;
    }

    .workers
    {
        grid-row: 12;
        grid-column: 1;
    }

    .domains
    {
        grid-row: 13;
        grid-column: 1;
    }

    .treatment
    {
        grid-row: 14;
        grid-column: 1;
    }



</style>
