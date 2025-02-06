select ii.item_id, ii.item_name, ii.rarity #it.parent_item_id
from item_info as ii
join item_tree as it
on ii.item_id = it.item_id
WHERE it.parent_item_id IN (
    select item_id FROM item_info WHERE rarity = 'RARE'
)
order by ii.item_id desc;