import React from "react";

import Item from "./Item"

export default function List({ title, items, onItemClick}){
    return(
        <>
            <h3 style={style.heading}>{title}</h3>
            <div className="list-group">
                {items.map(item => (
                    <Item key={item.id} item={item} onChange={onItemClick} title={title}/>
                ))}
            </div>
        </>

    );
}

const style = {
    heading: {
        fontFamily: "courier new"
    }
};
