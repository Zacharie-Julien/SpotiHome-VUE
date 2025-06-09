
import { createWebHistory, createRouter } from 'vue-router';
import { createApp } from 'vue'
import routes from './routes'
import App from './App.vue'

const router = createRouter({
    history: createWebHistory(),
    routes,
})

router.beforeEach((to, from, next) => {
  const token = document.cookie.split('.')[1];

  if (!token && to.name !== 'login' && to.name !== 'inscription') {
    return next({ name: 'login' });
  }

  if (token) {
        try {
            const payload = JSON.parse(atob(token));
            const requireRole = to.meta.requireRole;

            if (payload.role !== requireRole) {
                if (to.name === 'login') {
                    if (payload.role === 'admin') {                        
                        return next({ name: 'homeAdmin' });    
                    } else {
                        return next({ name: 'home' });    
                    }                    
                }
                if (to.name !== 'unauthorized') {
                    return next({ name: 'unauthorized' });
                }
                return next();
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
app.mount('#app');

