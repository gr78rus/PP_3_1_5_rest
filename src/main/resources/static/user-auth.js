const userName = $('#headerUserName');
const userRoles = $('#headerRole');
const userTable = $('#userTable');

userAuth();

function userAuth() {
    fetch("http://localhost:8080/api/principal")
        .then(res => res.json())
        .then(data => {
            userName.append(data.email);
            let roles = data.roles.map(role => " " + role.name.substring(5));
            userRoles.append(roles);
            let user = `$(
                <tr>
                    <td>${data.id}</td>
                    <td>${data.name}</td>
                    <td>${data.surname}</td>
                    <td>${data.age}</td>
                    <td>${data.email}</td>
                    <td>${roles}</td>
                </tr>)`;
            userTable.append(user);
        })
}