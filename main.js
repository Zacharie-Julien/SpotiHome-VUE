
import { createWebHistory, createRouter } from 'vue-router';
import VueApexCharts from "vue3-apexcharts";
import { createApp } from 'vue'
import PrimeVue from 'primevue/config'
import routes from './routes'
import App from './App.vue'
import { loginUser } from './services/loginServices';
import ToastService from 'primevue/toastservice';
import Toast from 'primevue/toast';

const router = createRouter({
    history: createWebHistory(),
    routes,
})

router.beforeEach((to, from, next) => {
  const token = document.cookie.split('.')[1];

  if (!token && to.name !== 'login') {
    return next({ name: 'login' });
  }

  if (token) {
        try {
            const payload = JSON.parse(atob(token));
            const requireRole = to.meta.requireRole;

            if (payload.role !== requireRole) {
                if (to.name === 'login') {
                    return next();
                }
                if (to.name !== 'unauthorized') {
                    return next({ name: 'unauthorized' });
                }
                return next();
            }

            if (to.name == 'modificationField' && from.name != 'field') {
                return next({ name: 'field' });
            }

        } catch (e) {
            if (to.name !== 'login') {
                return next({ name: 'login' });
            }
        }
    }
    next();
});

const app = createApp(App);
app.use(router);
app.use(VueApexCharts);
app.mount('#app');
app.use(PrimeVue);
app.use(ToastService);

