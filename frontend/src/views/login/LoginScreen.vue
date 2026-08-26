<template>
  <h1>PETURN</h1>
  <h2>Glad to see you here!</h2>

  <form action="" method="get">
    <input type="email" name="email" id="email" placeholder="Email" />
    <input type="password" name="password" id="password" placeholder="Password" />
    <input type="submit" value="Login" />
  </form>

  <p>New around here?</p>

  <RouterLink to="/create_account">Create Account</RouterLink>
</template>
