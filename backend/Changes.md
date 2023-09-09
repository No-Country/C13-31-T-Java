### SecurityConfig
 
- Added <span style= "color:orange"> getUserNameFromToken() </span> method to extract username from token and return as String
- Added <span style= "color:orange"> server.error.include-stacktrace=never </span> to aplication.properties to avoid sending error trace on exceptions
- Implementation of <span style= "color:orange"> ENUM.valueOf() </span> to assign RolesNames and AreasNames to new users
- Added <span style= "color:orange"> @ExceptionHandler </span> to AuthController to personalize validation error message, and added message to DTO
- Fix relations on Request entity
- Fix relations on Bid Entity
- Fix relations on ImageR Entity

### HU-B-4

- Added Try/Catch in JwtAuthorizationFilter to manage exception on database query
- 