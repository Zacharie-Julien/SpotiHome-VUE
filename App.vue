
<template>
  
  <Layout v-if="isDefined">
        <template v-slot:header>
          <Header></Header>
        </template>
        <template v-slot:main>
          <RouterView/>
        </template>
        <template v-slot:footer>
            <Footer></Footer>          
        </template>
  </Layout>

  <div v-else class="login">
    <RouterView/>
  </div>

</template>

<script setup>

  import { ref, onMounted } from 'vue';
  import Layout from './layouts/Layout.vue';
  import Header from './components/Header.vue';
  import Footer from './components/Footer.vue';

  var isDefined = ref(false);

  onMounted(() => {
    if (document.cookie != '') {
      isDefined.value = true;
    }
  });

</script>

<style scoped>

  .login 
  {
    grid-row: 3 / 8;
    grid-column: 3 / 5;
  }

</style>
