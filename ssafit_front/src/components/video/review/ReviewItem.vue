<template>
  <div>
    <p>{{review.review_content}} {{review.user_id}} {{review.reg_date}}</p>
  </div>
</template>

<script>
export default {
  name: 'ReviewItem',
  
  props: {
    review: '',
  },
}
</script>

<style>

</style>