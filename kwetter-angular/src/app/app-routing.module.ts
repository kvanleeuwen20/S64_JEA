import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MessagesComponent} from './messages/messages.component';
import {ProfileComponent} from './profile/profile.component';
import {UsersComponent} from './users/users.component';
import {LoginComponent} from './login/login.component';
import {FollowingComponent} from './following/following.component';
import {FollowersComponent} from './followers/followers.component';
import {UserDetailsComponent} from './user-details/user-details.component';
import {TimelineComponent} from './timeline/timeline.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'users', component: UsersComponent },
  { path: 'timeline/:id', component: TimelineComponent },
  { path: 'profile/:id', component: ProfileComponent },
  { path: 'login', component: LoginComponent },
  { path: 'messages/:id', component: MessagesComponent, outlet: 'secondary' },
  { path: 'details/:id', component: UserDetailsComponent, outlet: 'secondary' },
  { path: 'following/:id', component: FollowingComponent, outlet: 'secondary' },
  { path: 'followers/:id', component: FollowersComponent, outlet: 'secondary' },
  { path: '**', redirectTo: '/login', pathMatch: 'full' }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
