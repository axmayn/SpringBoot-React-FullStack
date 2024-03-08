const UserProfile = ({name, age, gender, index, ...asdf}) => {

    gender=gender==="male"?"men":"women";
    return (
        <div>
            <h2>{name}</h2>
            <h5>{age}</h5>
            <img src={`https://randomuser.me/api/portraits/med/${gender}/${index}.jpg`}/>
        {asdf.children}
        </div>
    );
}

export default UserProfile;